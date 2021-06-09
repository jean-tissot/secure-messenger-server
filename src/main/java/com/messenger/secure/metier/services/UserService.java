package com.messenger.secure.metier.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.messenger.secure.metier.dto.User;
import com.messenger.secure.metier.repository.UserRestRepository;
import com.messenger.secure.utils.Utils;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Resource
    private UserRestRepository userRestRepository;

    public User getUser(int userId) {
        Optional<User> user = userRestRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null; // TODO: lever erreur 404
        }
    }

    // Pour être ami avec qqn il faut qu'il soit dans nos amis et être dans les siens
    // Pour faire une demande d'amitié il faut ajouter la personne à la liste de ses amis
    // Pour accepter une demande d'ami il faut faire de même
    public void AddOrAcceptFriend(int userId, int friendId) {
            getUser(userId).getFriends().add(getUser(friendId));
    }

    // Rejeter une demande d'ami ou supprimer un ami
    public void removeFriend(User user, User friend) {
        List<User> friends = user.getFriends();
        List<User> friendOf = user.getFriendOf();
        while (friends.contains(friend)) {
            friends.remove(friend);
        }
        while (friendOf.contains(friend)) {
            friendOf.remove(friend);
        }
    }

    // la liste des personnes nous demandant en ami est composé de ceux qui sont dans
    // friendOf mais pas dans friend
    public List<User> getFriendAsksTo(int userId) {
        User user = getUser(userId);
        return Utils.minus(user.getFriendOf(), user.getFriends());
    }

    // la liste des demandes d'amis est composé de ceux qui sont dans friend mais pas dans friendOf
    public List<User> getFriendAsksFrom(int userId) {
        User user = getUser(userId);
        return Utils.minus(user.getFriends(), user.getFriendOf());
    }

    public List<User> getFriends(int userId) {
        User user = getUser(userId);
        return Utils.intersect(user.getFriends(), user.getFriendOf());
    }



}
