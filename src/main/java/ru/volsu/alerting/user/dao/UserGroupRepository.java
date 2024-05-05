package ru.volsu.alerting.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volsu.alerting.user.model.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
    UserGroup findByName(String userGroupName);
}
