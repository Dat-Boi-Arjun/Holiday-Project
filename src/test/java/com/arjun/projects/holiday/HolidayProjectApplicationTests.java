package com.arjun.projects.holiday;

import com.arjun.projects.database.UserDatabase;
import com.arjun.projects.database.WishlistDatabase;
import com.arjun.projects.entity.User;
import com.arjun.projects.entity.WishlistItem;
import com.arjun.projects.service.ProfileService;
import com.arjun.projects.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class HolidayProjectApplicationTests {

    @Mock
    public UserDatabase userDBMock;

    @Mock
    public WishlistDatabase wishlistDBMock;


    @InjectMocks
    public UserService userService;

    @InjectMocks
    public ProfileService profileService;

    public User testUser = new User("Testee", 50.0);

    public User alice = new User("Alice", 200.0);
    public WishlistItem aliceItem1 = new WishlistItem("Alice", "Lamp", 50.0);
    public WishlistItem aliceItem2 = new WishlistItem("Alice", "Keyboard", 40.0);
    public WishlistItem aliceItem3 = new WishlistItem("Alice", "Eraser", 2.0);

    public User bob = new User("Bob", 150.0);
    public WishlistItem bobItem1 = new WishlistItem("Bob", "USB Hub", 25.0);
    public WishlistItem bobItem2 = new WishlistItem("Bob", "Notebook", 15.0);

    public User chris = new User("Chris", 100.0);
    public WishlistItem chrisItem1 = new WishlistItem("Chris", "Mechanical Pencil", 10.0);


    @BeforeEach
    public void userSetup() {
        System.out.println("Running setup...");

        when(userDBMock.findByName("Testee")).thenReturn(this.testUser);

        when(userDBMock.findByName("Alice")).thenReturn(this.alice);
        when(userDBMock.findByName("Bob")).thenReturn(this.bob);
        when(userDBMock.findByName("Chris")).thenReturn(this.chris);

        List<WishlistItem> mockPriceMatchList = Arrays.asList(
                this.aliceItem2, this.bobItem1,
                this.aliceItem1, this.chrisItem1,
                this.bobItem2, this.aliceItem3);
        when(wishlistDBMock.priceMatch(this.testUser.getMaxPrice())).thenReturn(mockPriceMatchList);


        System.out.println("Setup completed");
    }

    @Test
    void contextLoads() {
    }

    @Test
   public void mustMatchAndOrderUsers() {
        assertEquals(Arrays.asList(this.alice, this.bob, this.chris), userService.matchUsers(this.testUser.getName()));
   }

   @Test
    public void mustApplyScoreWhenMatchingUsers() {
        this.bob.setScore(2);
        this.chris.setScore(1);

        assertEquals(Arrays.asList(this.bob, this.alice, this.chris), userService.matchUsers(this.testUser.getName()));
   }

   @Test
   public void shouldOrderWishlistByPrice() {
        WishlistItem item1 = new WishlistItem("Dave", "Case", 10.0);
        WishlistItem item2 = new WishlistItem("Dave", "Monitor", 150.0);
        WishlistItem item3 = new WishlistItem("Dave", "Phone", 600.0);
        List<WishlistItem> mockWishlistItems = Arrays.asList(item2, item3, item1);

        when(wishlistDBMock.findUserWishlist("Dave")).thenReturn(mockWishlistItems);

        assertEquals(Arrays.asList(item3, item2, item1), userService.getWishlist("Dave"));
   }

   @Test
    public void shouldFindUsersWishlistsWhenMatching() {
        List<WishlistItem> aliceItems = Arrays.asList(this.aliceItem1, this.aliceItem2, this.aliceItem3);
        List<WishlistItem> bobItems = Arrays.asList(this.bobItem1, this.bobItem2);
        List<WishlistItem> chrisItems = Collections.singletonList(this.chrisItem1);

       when(wishlistDBMock.findUserWishlist("Alice")).thenReturn(aliceItems);
       when(wishlistDBMock.findUserWishlist("Bob")).thenReturn(bobItems);
       when(wishlistDBMock.findUserWishlist("Chris")).thenReturn(chrisItems);


        assertEquals(Arrays.asList(aliceItems, bobItems, chrisItems),
                userService.matchUserWishlists(this.testUser.getName()));
   }

}
