//package com.company;

public class InputVerification {


    public String Validate_Email_Password(String Email,String password,String Phone)
    {
        String status = "";
        if(Email.isEmpty() || password.isEmpty())//first check if any field has been left empty
        {
            status="Invalid Email Or Password"; //invalid email or password
        }
        if(!Email.contains("@"))//check for correct email format
        {
            status=status.concat(" ,Invalid Email"); // invalid email
        }
        if (!((password.length() >= 4) ))//password max length=20 and min length=4
        {
            status=status.concat(" ,Invalid Password: Weak"); //invalid password
        }
         //check if password contains digits

            int Integer_count = 0;


            for (int i=0;i<10;i++)
            {
               String num = Integer.toString(i);
               if (password.contains(num))
               {
                   Integer_count++;
               }
            }
            if (Integer_count == 0) //no digits were present
            {
                status=status.concat(" ,Invalid Password:No Digits Present");
            }


        int count=0;
        for (int i= 65; i<= 90; i++)//check that capital letters are present in the password
        {
            char c = (char)i;

            String capitalChar = Character.toString(c);
            if (password.contains(capitalChar)) {
                count = 1;
            }
        }
        if (count == 0) {
            status=status.concat(" ,Invalid Password: No Capital Letters were present");
        }
        ///check for phone number
        if(Phone.length()==13)
        {
            if(!Phone.contains("+"))
            {
                status=status.concat(" ,Invalid Phone Number");
            }

        }
        if((Phone.contains("-")) )
        {
            if( (Phone.length()!=12) )
            {
                System.out.println(Phone.length());
                status = status.concat(" ,Invalid Phone Number");
            }


        }
        //System.out.println(status);
        if(status.equals(""))
             return "Valid";//email ,password and phone number are all correct
        else
            return status;
    }

}
