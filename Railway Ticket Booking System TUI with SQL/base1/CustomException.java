package base1;

public class CustomException extends Exception {
	public static class InvalidUsernameExp extends Exception{
		public InvalidUsernameExp() {
			super("Username cannot be more than 50 characters");			
		}
	}
	public static class InvalidEmailExp extends Exception{
		public InvalidEmailExp() {
			super("Email does not meet the required criteria");			
		}
	}
	public static class InvalidNameExp extends Exception{
		public InvalidNameExp() {
			super("Name cannot be more than 70 characters or Name should cannot contain Numeric Characters");
		}
	}
	public static class InvalidContactExp extends Exception{
		public InvalidContactExp() {
			super("Contact cannot be more than 50 characters");
		}
	}
}