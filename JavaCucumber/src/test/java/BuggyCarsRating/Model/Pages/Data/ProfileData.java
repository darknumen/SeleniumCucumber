package BuggyCarsRating.Model.Pages.Data;

public class ProfileData {

    public String FirstName;
    public String LastName;
    public String Gender;
    public String Age;
    public String Address;
    public String Phone;


    public ProfileData WithFirstName(String firstname)
    {
        this.FirstName = firstname;
        return this;
    }
    public ProfileData WithLastName(String lastname)
    {
        this.LastName = lastname;
        return this;
    }
    public ProfileData WithGender(String gender)
    {
        this.Gender = gender;
        return this;
    }
    public ProfileData WithAge(String age)
    {
        this.Age = age;
        return this;
    }
    public ProfileData WithAddress(String address)
    {
        this.Address = address;
        return this;
    }
    public ProfileData WithPhone(String phone)
    {
        this.Phone = phone;
        return this;
    }


    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof ProfileData))
            return false;
        ProfileData p = (ProfileData) other;
        return p.FirstName.equals(this.FirstName) && p.LastName.equals(this.LastName) && p.Gender.equals(this.Gender) &&
                p.Age.equals(this.Age) && p.Address.equals(this.Address) && p.Phone.equals(this.Phone);
    }
}
