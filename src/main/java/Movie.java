public class Movie extends Item {
    private int year;
    private String director;
    private Integer rating = null;

    public Movie(int id, String title, int year, String director) {
        super(id, title);
        this.year = year;
        this.director = director;
    }

    public Movie(int id, String title, int year, String director, int rating) {
        super(id, title);
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String toString() {
        String ratingString;

        if (rating == null) {
            ratingString = "unrated";
        } else {
            ratingString = rating.toString();
        }

        return "Movie{" +
                "id=" + id +
                ", title=" + title +
                ", year=" + year +
                ", director=" + director +
                ", rating=" + ratingString +
                '}';
    }
}
