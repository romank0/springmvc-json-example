package org.romako.simpleirc;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Ignore;
import org.junit.Test;

public class MessageIT {

	private static final String ANY_TEXT = "Hello reviewer!";
	private SimpleIrcDriver sutDriver = new SimpleIrcDriver("http://localhost:8080/api");
	
	@Test
	@Ignore
	public void messageListIsEmptyByDefault() throws Exception {
		assertThat(messagesInSut(), is(empty()));
	}

	@Test
	public void messageListContainsMessageAfterPostingIt() throws Exception {
		Message message = new Message(ANY_TEXT);
		postMessageToSut(message);
		assertThat(messagesInSut(), hasItem(equalTo(message)));
	}

	private void postMessageToSut(Message message) throws Exception {
		sutDriver.post(String.format("/message/%s", message.getText()), null);
		//sutDriver.post("/message", message);
	}

	private List<Message> messagesInSut() throws Exception {
		return Arrays.asList(sutDriver.get("/message", Message[].class));
	}

	private Matcher<Collection<Message>> empty() {
		return new TypeSafeMatcher<Collection<Message>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("empty collection");
			}

			@Override
			public boolean matchesSafely(Collection<Message> collection) {
				return collection.isEmpty();
			}
		};
	}

}
