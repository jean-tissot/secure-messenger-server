package com.messenger.secure.metier.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.messenger.secure.metier.dto.Group;
import com.messenger.secure.metier.dto.Message;
import com.messenger.secure.metier.dto.User;
import com.messenger.secure.metier.dto.UserGroup;
import com.messenger.secure.metier.repository.GroupRestRepository;
import com.messenger.secure.metier.repository.UserGroupRestRepository;
import com.messenger.secure.metier.repository.UserRestRepository;

import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Resource
    private GroupRestRepository groupRestRepository;
    @Resource
    private UserGroupRestRepository userGroupRestRepository;
    @Resource
    private UserRestRepository userRestRepository;
    @Resource
    private UserService userService;

    public Group getGroup(int groupId) {
        Optional<Group> group = groupRestRepository.findById(groupId);
        if (group.isPresent()) {
            return group.get();
        } else {
            return null; // TODO: lever erreur 404
        }
    }

    public List<User> getUsers(int groupId) {
        return getGroup(groupId).getUserGroups().stream()
            .map(userGroup -> userGroup.getUser())
            .collect(Collectors.toList());
    }

    public List<Message> getMessages(int groupId) {
        return getGroup(groupId).getMessages();
    }

    public void addUser(int groupId, int userId) {
        addUser(groupId, userId, 0);
    }

    public void addUser(int groupId, int userId, int roleId) {
        User user = userService.getUser(userId);
        Group group = getGroup(groupId);
        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);
        userGroup.setRoleId(roleId);
        userGroupRestRepository.save(userGroup);
    }

    @Transactional
    public void removeUser(int groupId, int userId) {
        User user = userService.getUser(userId);
        Group group = getGroup(groupId);
        userGroupRestRepository.deleteByGroupAndUser(group, user);
    }
    
}
