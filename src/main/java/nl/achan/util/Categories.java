package nl.achan.util;

/**
 * A list of categories.
 *
 * Created by Etienne on 9-6-2017.
 */
public enum Categories {
    TECHNOLOGY("technology"),
    POLITICS("politics"),
    SPORTS("sports"),
    MISCELLANEOUS("miscellaneous"),
    GAMING("gaming");

    private final String name;

    private Categories(final String name){
        this.name = name;
    };

    @Override
    public String toString() {
        return name;
    }
}
