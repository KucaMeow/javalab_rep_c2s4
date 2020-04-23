package ru.itis.scopes;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.config.security.details.UserDetailsImpl;
import ru.itis.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserScope implements Scope {

    public static Map<User, Map<String, Object>> objects = new HashMap<>();

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        if(!objects.containsKey(user))
            objects.put(user, new HashMap<>());
        if(objects.get(user).get(s) == null) {
            objects.get(user).put(s, objectFactory.getObject());
        }
        return objects.get(user).get(s);
    }

    @Override
    public Object remove(String s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Object obj = objects.get(user).get(s);
        objects.get(user).remove(s);
        return obj;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {
    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
