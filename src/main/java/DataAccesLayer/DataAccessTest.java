package DataAccesLayer;
import java.time.LocalDate;
import java.util.List;

import Entities.AppUser;
import Entities.Folio;
import Entities.Guest;
import Entities.Room;

public class DataAccessTest {
    static DataAccess dataAccess = new DataAccess();
    public static void main(String args[]){
        AppUser addAppUser = new AppUser();
        addAppUser.setName("Furkan");
        addAppUser.setSurname("Şahin");
        addAppUser.setUsername("testUsername");
        addAppUser.setPassword("testPassword");
        testAddAppUser(addAppUser);
        testCheckUserForLogin(addAppUser);

        Room room = new Room();
        room.setClean(false);
        room.setEmpty(false);
        room.setFolios(new Folio());
        room.setRoomID(1);
        testCreateRoom(room);
        testGetAllRooms();
        testGetRoomById(1);

        Guest guest = new Guest();
        guest.setName("Furkan");
        guest.setSurname("Şahin");
        guest.setTC("12312312312");
        guest.setCheckIn(LocalDate.now());
        guest.setCheckOut(LocalDate.now());
        testAddGuestToRoom(guest, 1);
        testRemoveGuestFromRoom(guest,1);

        Guest guest2 = new Guest();
        guest2.setName("Furkan");
        guest2.setSurname("Şahin");
        guest2.setTC("12312312312");
        guest2.setCheckIn(LocalDate.of(2023, 12, 20));
        guest2.setCheckOut(LocalDate.of(2023, 12, 25));
        dataAccess.AddGuestToRoom(guest2, 1);
        testCheckDaysOfGuest("12312312312", 1);//expected value 5 because of dates

        Guest guest3 = new Guest();
        guest3.setName("Furkan");
        guest3.setSurname("Şahin");
        guest3.setTC("12312312312");
        guest3.setCheckIn(LocalDate.of(2023, 12, 20));
        guest3.setCheckOut(LocalDate.now());
        dataAccess.AddGuestToRoom(guest3, 1);
        testControlCheckOutDate("12312312312", 1);
        testGetGuestListByRoom(1);

        testAddProductToFolio(1,"Test",50d);
        testGetAllFolio(1);
        testDeleteProductFromFolio(1,"Test");

        dataAccess.appUsers.clear();
        dataAccess.rooms.clear();

    }
    public static void testAddAppUser(AppUser appUser)
    {
        dataAccess.AddUser(appUser);
        if(dataAccess.appUsers.contains(appUser))
        {
            System.out.println("Add user success");
        }
        else
        {
            System.out.println("Add user failed");
        }
    }

    public static void testCheckUserForLogin(AppUser checkAppUser)
    {
        if(dataAccess.CheckUserForLogin(checkAppUser)==1){
            System.out.println("Check user login success");
        }
        else{
            System.out.println("Check user login failed");
        }
    }

    public static void testCreateRoom(Room createRoom){
        dataAccess.CreateRoom(createRoom);
        if(dataAccess.rooms.contains(createRoom)){
            System.out.println("Create room success");
        }
        else{
            System.out.println("Create room failed");
        }
    }
    
    public static void testGetAllRooms()
    {
        if(dataAccess.rooms.size()>0)
        {
            System.out.println("Get all room success");
        }
        else{
            System.out.println("Get all room failed");
        }
    }

    public static void testGetRoomById(int id)
    {
        Room room = dataAccess.GetRoomByID(id);
        if(dataAccess.rooms.contains(room))
        {
            System.out.println("Get room by id success");
        }
        else{
            System.out.println("Get room by id failed");
        }
    }

    public static void testAddGuestToRoom(Guest guestAdd, int roomId)
    {
        int check = dataAccess.AddGuestToRoom(guestAdd, roomId);
        if(check == 1 ){
            System.out.println("Add guest to room success");
        }
        else{
            System.out.println("Add guest to room failed");
        }

    }

    public static void testRemoveGuestFromRoom(Guest removGuest,int roomId){
        if(dataAccess.RemoveGuestFromRoom(removGuest, roomId)==1){
            System.out.println("Remove guest from room success");
        }
        else{
            System.out.println("Remove guest from room failed");
        }
    }

    public static void testCheckDaysOfGuest(String GuestTc, int roomId){
        int expectedValue = dataAccess.CheckDaysOfGuest(GuestTc, roomId);
        //expected value 5 because of dates
        if(expectedValue==5){
            System.out.println("Check days of guest success");
        }
        else{
            System.out.println("Check days of guest failed");
        }
        
    }

    public static void testControlCheckOutDate(String GuestTc, int RoomId)
    {
        int expectedValue = dataAccess.ControlCheckOutDate(GuestTc, RoomId);
        //expected value localdate.now - checkoutDate(we think checkoutDate=now) so expectedValue = 0
        if(expectedValue==0)
        {
            System.out.println("Control checkout date success");
        }
        else{
            System.out.println("Control checkout date failed");
        }
    }

    public static void testGetGuestListByRoom(int roomId)
    {
        List<Guest> testListGuest = dataAccess.GetGuestListByRoom(roomId);
        if(testListGuest.size()>0){
            System.out.println("Get guest list by room success");
        }
        else{
            System.out.println("Get guest list by room failed");
        }
    }

    public static void testAddProductToFolio(int roomId, String productName, Double price)
    {
        dataAccess.AddProductToFolio(roomId, productName, price);
        Folio testFolio = dataAccess.GetAllFolio(roomId);
        boolean expectedValue = testFolio.getProducts().containsKey(productName);
        if(expectedValue)
        {
            System.out.println("Add product to folio success");
        }
        else{
            System.out.println("Add product to folio failed");
        }
        
    }

    public static void testGetAllFolio(int roomId)
    {
        Folio folio = dataAccess.GetAllFolio(roomId);
        if(folio.getProducts().size()>0)
        {
            System.out.println("Get all folio success");
        }
        else{
            System.out.println("Get all folio failed");
        }
    }
    
    public static void testDeleteProductFromFolio(int roomId,String productName)
    {
        dataAccess.DeleteProductFromFolio(roomId, productName);
        Folio folio = dataAccess.GetAllFolio(roomId);
        if(!folio.getProducts().containsKey(productName)){
            System.out.println("Delete product from folio success");
        }
        else{
            System.out.println("Delete product from folio failed");
        }
    }

}
