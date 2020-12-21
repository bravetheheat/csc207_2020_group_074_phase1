package main.presenters;

/**
 * The ExportEventScreen is a presenter class that displays Admin user option of export event to HTML.
 *
 * @author Leyi Wang
 * @version 1.0
 */
@Deprecated
public class ExportEventScreen {

    public ExportEventScreen(){

    }

    public void printScreenName() {
        System.out.println("Export Event Screen");
        System.out.println();
    }

    /**
     * Print available options for users to choose.
     */
    public void optionsPrompt() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("0. Return to previous screen.");
        System.out.println("1. Export event schedule to HTML");
    }

    public void exportEventOption(){
        System.out.println("You are exporting event list of an user by her/his username:");
        System.out.println("Enter user name: ");
    }
    /**
     * Print error of input.
     */
    public void invalidInput(){
        System.out.println("Sorry, your input is invalid. Please try again.");
        System.out.println();
    }

    public void failure(){
        System.out.println("Sorry, you fail to export events ");
        System.out.println();
    }

    public void success(){
        System.out.println("Successfully export events to HTML ");
        System.out.println();
    }
}
