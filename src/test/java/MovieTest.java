import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {
    @Test
    public void testToStringContainAllInformation() {
        Movie movieRated = new Movie(1, "Movie A", 2011,
                "Dafu", 10);
        Movie movieUnrated = new Movie(2, "Movie B", 2011,
                "Dafu");

        assertEquals("Movie{id=1, title=Movie A, year=2011, " +
                "director=Dafu, rating=10}", movieRated.toString());
        assertEquals("Movie{id=2, title=Movie B, year=2011, " +
                "director=Dafu, rating=unrated}", movieUnrated.toString());
    }
}
