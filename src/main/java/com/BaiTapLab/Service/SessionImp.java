package com.BaiTapLab.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BaiTapLab.Model.Session;


@Service
public class SessionImp implements SessionService {
	public static List<Session> listSession = new ArrayList<>();

	@Override
	public Object get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Session item : listSession) {
			if (key.equalsIgnoreCase(item.getKey())) {
				return item.getValue();
			}
		}
		return null;
	}

	@Override
	public void set(String key, Object value) {
		listSession.removeIf(item -> key.equalsIgnoreCase(item.getKey()));
		Session session = new Session();
		session.setKey(key);
		session.setValue(value);
		listSession.add(session);
	}
}

