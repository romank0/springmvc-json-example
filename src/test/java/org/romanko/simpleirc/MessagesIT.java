package org.romanko.simpleirc;

import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class MessagesIT {

	@Test
	public void messagesAreEmptyByDefault() {
		assertThat(messages(), is(empty());
	}

}
