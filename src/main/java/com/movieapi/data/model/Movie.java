package com.movieapi.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String year;
    private String title;
    private String studios;
    private String producers;
    private String winner;
    
    public Movie() {

    }
   
	public Long getId() {
		return id;
	}

	public String getYear() {
		return year;
	}

	public String getTitle() {
		return title;
	}

	public String getStudios() {
		return studios;
	}

	public String getProducers() {
		return producers;
	}

	public String getWinner() {
		return winner;
	}

	@ManyToMany()
	@JoinTable(
		name = "movie_producer", 
		joinColumns = @JoinColumn(name = "movie_id"),
		inverseJoinColumns = @JoinColumn(name = "producer_id"))
	private Set<Producer> movieProducer;
	
	public Set<Producer> getMovieProducer() {
		return movieProducer;
	}

	public void setMovieProducer(Set<Producer> movieProducer) {
		this.movieProducer = movieProducer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", year=" + year + ", title=" + title + ", studios=" + studios + ", producers="
				+ producers + ", winner=" + winner + "]";
	}
}