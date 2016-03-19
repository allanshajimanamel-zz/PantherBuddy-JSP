package pantherbuddy.business.view;

import java.util.List;

import pantherbuddy.business.model.MessageModel;

/**
 * Represents the view for the view messages use case.
 *
 * @author ALLAN
 *
 */
public class ViewMessagesForm implements Form {

	/**
	 * The messages to display
	 */
	public List<MessageModel> messages;

	/**
	 * Is used for pagination on the view messages view. Represents the starting
	 * number of the range of messages displayed on the page.
	 */
	public Integer rangeFrom;

	@Override
	public void loadPage() {
		// TODO Auto-generated method
	}

	@Override
	public void submitPageDataAndAction() {
		// TODO Auto-generated method
	}
}
