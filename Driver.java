import java.util.Scanner;
import java.io.*;

public class Driver
{
    public static void processComplaint(Complaint c) throws StateComplaintException
    {
        try{
            if(c.getCauseOfAction().equals("Equal Protection Challenge") || c.getCauseOfAction().equals("Title IX Workplace Discrimination") || c.getCauseOfAction().equals("Prisoner Civil Rights Claim") || c.getCauseOfAction().equals("Fair Labor Standard Act Claim"))
            {
                return;
            }
            if(c.getDefendantCitizenship().equals(c.getPlaintiffCitizenship()))
            {
                throw new StateComplaintException("Lack of Diversity");
            }
            else if(c.getAmountInControversy() <= 75000)
            {
                throw new StateComplaintException("Amount in controversy less than or equal to $75000");
            }
            else if(c.getDefendantCitizenship().equals(c.getOriginalStateOfFilling()))
            {
                throw new StateComplaintException("No prejudice through diversity");
            }
        }
        catch(StateComplaintException e)
        {

           throw new StateComplaintException(e.getMessage());

        }

    }


    public static void main(String[] args)
    {
        try
        {
            Scanner input = new Scanner(System.in);
            int acceptedCounter = 0;
            int deniedCounter = 0;

            System.out.println("[Federal Court Complaint Processor]");
            System.out.print("Enter file name to process: ");
            String file = input.nextLine();

            String fileLocation = "C:\\Users\\tyler\\IdeaProjects\\Assignment 6\\src\\" + file;

            Scanner fileRead = new Scanner(new File(fileLocation));
            //FileWriter files = new FileWriter("accepted.txt");
            //FileWriter files2 = new FileWriter("remanded.txt");

            PrintWriter files = new PrintWriter("accepted.txt");
            PrintWriter files2 = new PrintWriter("remanded.txt");


            while(fileRead.hasNextLine())
            {
                String[] StringHolder = fileRead.nextLine().split(",", 5);

                String causeOfAction = StringHolder[0];
                double amountInControversy = Double.parseDouble(StringHolder[1]);
                String plaintiffCitizenship = StringHolder[2];
                String defendantCitizenship = StringHolder[3];
                String originalStateOfFilling = StringHolder[4];


                Complaint c = new Complaint(causeOfAction, plaintiffCitizenship, defendantCitizenship, originalStateOfFilling, amountInControversy);

                try
                {
                    processComplaint(c);
                    files.println("Case ID: " + c.getId() + "\n" +
                            "Cause of action: " + c.getCauseOfAction() + "\n" +
                            "Amount in Controversy: " + c.getAmountInControversy() + "\n" +
                            "Plaintiff's Citizenship: " + c.getPlaintiffCitizenship() + "\n" +
                            "Defendant's Citizenship: " + c.getDefendantCitizenship() + "\n" +
                            "Originally filed in: " + c.getOriginalStateOfFilling() + "\n" +
                            "==============================");

                    acceptedCounter++;

                }
                catch(StateComplaintException e)
                {
                    files2.println("Case ID: " + c.getId() + "\n" +
                            "Cause of action: " + c.getCauseOfAction() + "\n" +
                            "Amount in Controversy: " + c.getAmountInControversy() + "\n" +
                            "Plaintiff's Citizenship: " + c.getPlaintiffCitizenship() + "\n" +
                            "Defendant's Citizenship: " + c.getDefendantCitizenship() + "\n" +
                            "Originally filed in: " + c.getOriginalStateOfFilling() + "\n" +
                            " " + "\n" + "Reason for remand" + e.getMessage() + "\n" +
                            "==============================");
                    deniedCounter++;
                }




            }
            System.out.println("Processing complete. Accepted cases written to accepted.txt and remanded cases written to remanded.txt");
            System.out.println("Total remanded cases: " + deniedCounter);
            System.out.println("Total accepted cases: " + acceptedCounter);
            System.out.println("Shutting down...");
        }

      /* catch(FileNotFoundException e)
        {
            String newMessage = e.getMessage().substring(45);
            String[] newestMessage = newMessage.split(" ", 2);
            System.out.println("No file with name " + newestMessage[0]);
            System.out.println("Shutting down...");
        }*/
        catch(IOException ioe)
        {
            String newMessage = ioe.getMessage().substring(45);
            String[] newestMessage = newMessage.split(" ", 2);
            System.out.println("No file with name " + newestMessage[0]);
            System.out.println("Shutting down...");
        }




    }
}