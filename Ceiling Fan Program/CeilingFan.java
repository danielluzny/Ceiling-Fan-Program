import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CeilingFan
{
	// A list of accepted user commands
	private final String CMD_PULL_1 = "pull 1";
	private final String CMD_PULL_2 = "pull 2";
	private final String CMD_CHECK_SPEED = "check speed";
	private final String CMD_CHECK_DIRECTION = "check direction";
	private final String CMD_EXIT = "exit";
	
	// A description of each command
	private final String DESCRIBE_PULL_1 = "-pull the first cord increasing or decreasing the speed based on the direction of the fan";
	private final String DESCRIBE_PULL_2 = "-pull the second cord which reverses the direction of the fan";
	private final String DESCRIBE_CHECK_SPEED = "-outputs the current fan speed";
	private final String DESCRIBE_CHECK_DIRECTION = "-outputs the current direction of the fan";
	private final String DESCRIBE_EXIT = "-exits the Ceiling Fan program";
	
	// A series of output strings based on user inputted commands
	private final String FAN_DIRECTION_NORMAL = "The fan is moving in it's normal direction";
	private final String FAN_DIRECTION_REVERSE = "The fan is moving in reverse";
	private final String PULLED_1_NORMAL = "Pulled the 1st cord, the fan speed increases";
	private final String PULLED_1_REVERSE = "Pulled the 1st cord, the fan speed decreases";
	private final String NORMAL_OVERFLOW = "Pulled the 1st cord, the fan speed cannot increase anymore and loops back to 'OFF'";
	private final String REVERSE_OVERFLOW = "Pulled the 1st cord, the fan speed cannot decrease anymore and loops over to maximum speed";
	private final String INVALID_CMD = "Invalid command entered. Try the following commands:\n";
	
	// Input exception string
	private final String EXCEPTION_STR = "I/O exception occured.";
	
	// Initial prompt string
	private final String INITIAL_PROMPT = "Welcome to the Ceiling Fan program. To get started, input one of the following commands:\n";
	
	// Default, minimum, and maximum fan speeds
	private final int DEFAULT_SPEED = 0;
	private final int MINIMUM_SPEED = 0;
	private final int MAXIMUM_SPEED = 3;
	
	private final boolean DEFAULT_DIRECTION = false;
	
	// The current speed of the fan
	private int speed;
	
	// A boolean responsible for determining which direction the fan is moving.
	private boolean reversed; 
	
	// Simple Constructor which sets vaules to default
	public CeilingFan()
	{
		speed = DEFAULT_SPEED;
		reversed = DEFAULT_DIRECTION;
	}
	
	// The main processing method for the Ceiling Fan
	public void processFan()
	{
		System.out.println(INITIAL_PROMPT);
		printCommands();
		String userCommand = getUserInput();
		
		while(!(userCommand.equalsIgnoreCase(CMD_EXIT))) // Continue until the user exits. Exiting requires no processing and simply exits the program.
		{
			processCommand(userCommand);
			userCommand = getUserInput();
		}
	}
	
	// Process the user's command
	private void processCommand(String userCommand)
	{
		if(userCommand.equalsIgnoreCase(CMD_PULL_1)) // Cord 1 increments or decrements speed based on direction of the fan
		{
			if(!reversed) // If the fan is spinning normally
			{
				if(this.speed<MAXIMUM_SPEED) // increase the speed so long as we aren't at the maximum. If we are, set speed to minimum.
				{
					this.speed++;
					System.out.println(PULLED_1_NORMAL);
				}
				else
				{
					this.speed = MINIMUM_SPEED;
					System.out.println(NORMAL_OVERFLOW);
				}
			}
			else // Else the fan is reversed
			{
				if(this.speed>MINIMUM_SPEED) // decrease speed so long as we aren't at the minumum. If we are, set speed to maximum.
				{
					this.speed--;
					System.out.println(PULLED_1_REVERSE);
				}
				else
				{
					this.speed = MAXIMUM_SPEED;
					System.out.println(REVERSE_OVERFLOW);
				}
			}
		}
		else if(userCommand.equalsIgnoreCase(CMD_PULL_2)) // Cord 2 reverses the direction
		{
			if(this.reversed == false)
			{
				this.reversed = true;
			}
			else
				this.reversed = false;
		}
		else if(userCommand.equalsIgnoreCase(CMD_CHECK_DIRECTION)) // Prints the direction to the user
		{
			if(this.reversed == false)
			{
				System.out.println(FAN_DIRECTION_NORMAL);
			}
			else
				System.out.println(FAN_DIRECTION_REVERSE);
		}
		else if(userCommand.equalsIgnoreCase(CMD_CHECK_SPEED)) // Prints the current speed to the user
		{
			System.out.println(this.speed);
		}
		else
		{
			System.out.println(INVALID_CMD);
			printCommands();
		}
	}
	
	// Acquires user input through a BufferedReader and returns it as a string
	private String getUserInput()
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		String userCommand = "";
		
		try // Read a command from the user
		{
			userCommand = input.readLine();
		}
		catch(Exception e) // The most likely exception is buffer overflow, although BufferedReader's default buffer is very large.
		{
			System.out.println(EXCEPTION_STR);
			e.printStackTrace();
		}
		
		return userCommand;
	}
	
	// Prints a list of commands and describes them
	private void printCommands()
	{
		System.out.println(CMD_PULL_1 +"\t" + DESCRIBE_PULL_1 + "\n"
									+ CMD_PULL_2 +"\t" + DESCRIBE_PULL_2 + "\n"
									+ CMD_CHECK_SPEED + "\t" + DESCRIBE_CHECK_SPEED + "\n"
									+ CMD_CHECK_DIRECTION + "\t" + DESCRIBE_CHECK_DIRECTION + "\n"
									+ CMD_EXIT + "\t" + DESCRIBE_EXIT + "\n"
									); 
	}
}