package com.coolpay.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Token {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private static String token;

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		Token.token = token;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", token=" + token + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (id != other.id)
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
