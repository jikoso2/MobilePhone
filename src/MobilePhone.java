import java.util.ArrayList;

public class MobilePhone {

    private ArrayList<Contact> contactsList = new ArrayList<>();


    public void add(Contact contact){
        if (isExist(contact.getName()))
            System.out.println("Contact already exist");
        else {
            contactsList.add(contact);
            if (isExist(contact.getName()))
                System.out.println("Successfully add contact");
            else
                System.out.println("Error in adding contact");
        }
    }

    public void print(){
        int amount = contactsList.size();
        String contact = "contacts";
        if (amount == 0) {
            System.out.println("You don't have any contacts");
        }
        if (amount == 1)
            contact = "contact";


        if(amount >= 1) {
            System.out.println("You have a " + amount + " " + contact);

            for (int i = 0; i < contactsList.size(); i++) {
                System.out.println("Name: " + contactsList.get(i).getName() + " Number: " + contactsList.get(i).getNumber());
            }
        }
    }

    public void remove(String name){

        int position = indexForAccount(name);

        if (position >= 0 ){
            contactsList.remove(position);
            if (!isExist(name))
                System.out.println("You successful remove: " + name + " " + contactsList.get(position).getNumber());
        }
        else
            System.out.println("Contact doesn't exist");
    }


    public void modifyNumber (String name, int number){
        System.out.println("You replaced: " + contactsList.get(indexForAccount(name)).getNumber() + " for: " + number);
        contactsList.get(indexForAccount(name)).setNumber(number);
    }
    public void modifyName (String name, String new_name){
        if(isExist(new_name))
            System.out.println("New contact name already exist");
        else {
            System.out.println("You replaced: " + contactsList.get(indexForAccount(name)).getName() + " for: " + new_name);
            contactsList.get(indexForAccount(name)).setName(new_name);
        }
    }

    public void search(String name){
        if(indexForAccount(name) == -1)
            System.out.println("Contact doesn't exist");
        else
            System.out.println("Name: " + name + " Number: " + contactsList.get(indexForAccount(name)).getNumber());

    }


    private int indexForAccount (String name){

        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    public boolean isExist(String name) {
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getName().equals(name))
                return true;
        }
        return false;
    }

}
