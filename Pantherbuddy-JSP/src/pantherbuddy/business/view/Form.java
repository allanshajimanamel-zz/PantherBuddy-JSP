package pantherbuddy.business.view;

/**
 * Represents the view interface that every view should implement. It provides
 * the common methods that every view should implement.
 *
 * @author ALLAN
 *
 */
public interface Form {

	/**
	 * The method is called to pass data and actions from the view to the
	 * controller to take action on them.
	 */
	public void submitPageDataAndAction();

	/**
	 * The method is called load the page data from the model.
	 */
	public void loadPage();

}
