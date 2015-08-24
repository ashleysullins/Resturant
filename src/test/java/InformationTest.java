import org.junit.*;
import static org.junit.Assert.*;

public class InformationTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Information.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Information firstInformation = new Information("McDonalds", "201 Cherry Lane", 1234567890, "Burgers", 1, 1);
    Information secondInformation = new Information("McDonalds", "201 Cherry Lane", 1234567890, "Burgers", 1, 1);
    assertTrue(firstInformation.equals(secondInformation));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Information myInformation = new Information("McDonalds", "201 Cherry Lane", 1234567890, "Burgers", 1, 1);
    myInformation.save();
    assertTrue(Information.all().get(0).equals(myInformation));
  }

  @Test
   public void find_findInformationInDatabase_true() {
     Information myInformation = new Information("McDonalds", "201 Cherry Lane", 1234567890, "Burgers", 1, 1);
     myInformation.save();
     Information savedInformation = Information.find(myInformation.getId());
     assertTrue(myInformation.equals(savedInformation));
   }

   @Test
    public void delete_deleteInformationInDatabase_false() {
      Information myInformation = new Information("McDonalds", "201 Cherry Lane", 1234567890, "Burgers", 1, 1);
      myInformation.delete();
      Information deleteInformation = Information.find((myInformation.getId()));
      assertFalse(myInformation.equals(deleteInformation));
    }

    @Test
    public void getName_testIfWorks_true() {
      Information myInformation = new Information("McDonalds", "201 Cherry Lane", 1234567890,   "Burgers", 1, 1);
      String theName = myInformation.getName();
      assertTrue(theName.equals("McDonalds"));
    }
}
