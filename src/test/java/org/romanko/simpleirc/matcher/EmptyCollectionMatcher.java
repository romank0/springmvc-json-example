package org.romanko.simpleirc.matcher;

import java.util.Collection;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EmptyCollectionMatcher extends TypeSafeMatcher<Collection<T>>{

	public void describeTo(Description description) {
		description.appendText("empty collection");
	}

	@Override
	public boolean matchesSafely(Collection<T> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
