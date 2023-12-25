package BusinessLayer;

import java.time.LocalDate;
import java.util.List;

import DataAccesLayer.DataAccess;
import Entities.AppUser;
import Entities.Folio;
import Entities.Guest;
import Entities.Room;

public class BLL {
    DataAccess DAL = new DataAccess();

    public int AddUser(String userName, String password, String name, String surname) {

        AppUser newAppUser = new AppUser();
        newAppUser.setUsername(userName);
        newAppUser.setName(name);
        newAppUser.setSurname(surname);
        newAppUser.setPassword(password);
        return DAL.AddUser(newAppUser);

    }

    public int CheckUserForLogin(String userName, String password) {
        AppUser checkAppUser = new AppUser();
        checkAppUser.setUsername(userName);
        checkAppUser.setPassword(password);
        return DAL.CheckUserForLogin(checkAppUser);

    }

    public List<Room> GetAllRooms() {
        return DAL.GetAllRooms();
    }

    public int CreateRoom(int roomID) {
        Room newRoom = new Room();
        newRoom.setRoomID(roomID);
        return DAL.CreateRoom(newRoom);
    }

    public Room GetRoomByID(int id) {
        return DAL.GetRoomByID(id);
    }

    public int AddGuestToRoom(String TC, String Name, String Surname, LocalDate CheckIn, LocalDate checkOut, int RoomId) {
        Guest newGuest = new Guest();
        if (CheckTcNo(TC) == 1) {
            newGuest.setTC(TC);
            newGuest.setName(Name);
            newGuest.setSurname(Surname);
            newGuest.setCheckIn(CheckIn);
            newGuest.setCheckOut(checkOut);
            DAL.AddGuestToRoom(newGuest, RoomId);
            return 1;
        }
        return -1;

    }
//    public int AddGuestToRoom(LocalDate CheckOut) {
//        Guest newGuest = new Guest();
//        if (CheckTcNo(TC) == 1) {
//            newGuest.setTC(TC);
//            newGuest.setName(Name);
//            newGuest.setSurname(Surname);
//            newGuest.setCheckIn(CheckIn);
//            DAL.AddGuestToRoom(newGuest, RoomId);
//            return 1;
//        }
//        return -1;
//
//    }

    public int CheckDaysOfGuest(String GuestTc, int RoomId) {
        if (CheckTcNo(GuestTc) == 1) {
            return DAL.CheckDaysOfGuest(GuestTc, RoomId);
        }
        return -1;

    }

    public int ControlCheckOutDate(String GuestTc, int RoomId) {
        if (CheckTcNo(GuestTc) == 1) {
            return DAL.ControlCheckOutDate(GuestTc, RoomId);
        }
        return -1;
    }

    public int CheckTcNo(String Tc) {
        if (Tc.length() == 11) {
            return 1;//Tc doğru kodu
        }
        return -1;//Tc yanlış kodu
    }

    public List<Guest> GetGuestListByRoom(int RoomId) {
        return DAL.GetGuestListByRoom(RoomId);

    }

    public void AddProductToFolio(int roomId, String productName, double productPrice) {

        Room room = GetRoomByID(roomId);
        Folio roomFolio = room.getFolios();
        double balance = roomFolio.getBalance();
        roomFolio.setBalance(balance + productPrice);
        DAL.AddProductToFolio(roomId, productName, productPrice);
    }

    public Folio GetAllFolio(int RoomId) {
        return DAL.GetAllFolio(RoomId);
    }

}
