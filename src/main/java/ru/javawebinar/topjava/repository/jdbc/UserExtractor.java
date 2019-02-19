package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Integer, User> map = new LinkedHashMap<>();
        User user;
        Set<Role> roles=null;
        Set<String> stringSetRoles = new HashSet<>();
        while (resultSet.next()) {
            stringSetRoles.clear();
            Integer id = resultSet.getInt("id");
            user = map.get(id);
            if (user == null) {

                user = new User();
                user.setId(id);
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setRegistered(resultSet.getDate("registered"));
                user.setEnabled(resultSet.getBoolean("enabled"));
                user.setPassword(resultSet.getString("password"));
                user.setCaloriesPerDay(resultSet.getInt("calories_per_day"));
                map.put(id, user);
                roles=Collections.synchronizedSet(EnumSet.noneOf(Role.class));
            }
            stringSetRoles.add(resultSet.getString("role"));
            for(String role:stringSetRoles){
                Objects.requireNonNull(roles);
                roles.add(Role.valueOf(role));
            }
            user.setRoles(roles);

        }

        return new ArrayList<>(map.values());
    }
}
