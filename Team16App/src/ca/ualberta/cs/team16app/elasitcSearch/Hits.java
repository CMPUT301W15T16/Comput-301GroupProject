package ca.ualberta.cs.team16app.elasitcSearch;

import java.util.Collection;

import ca.ualberta.cs.team16app.elasitcSearch.*;


public class Hits<T> {
    int total;
    double max_score;
    Collection<ElasticSearchResponse<T>> hits;
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits;
    }
    public String toString() {
        return (super.toString()+","+total+","+max_score+","+hits);
    }
}