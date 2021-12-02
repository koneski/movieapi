package com.movieapi.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "producer")
public class Producer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name_producer", nullable = false)
	private String nameProducer;
	
	public Producer(String nameProducer) {
		this.nameProducer = nameProducer;
	}

	public Producer() {

	}

	@ManyToMany(mappedBy = "movieProducer")
    private Set<Movie> movies;

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public String getNameProducer() {
		return nameProducer;
	}

	public void setNameProducer(String nameProducer) {
		this.nameProducer = nameProducer;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nameProducer == null) ? 0 : nameProducer.hashCode());
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
		Producer other = (Producer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nameProducer == null) {
			if (other.nameProducer != null)
				return false;
		} else if (!nameProducer.equals(other.nameProducer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producer [id=" + id + ", nameProducer=" + nameProducer + ", movies=" + movies + "]";
	}
}