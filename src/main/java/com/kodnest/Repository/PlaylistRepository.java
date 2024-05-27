package com.kodnest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.entity.Playlist;

public interface PlaylistRepository  extends JpaRepository<Playlist, Integer>{
	Playlist findFirstByName(String name);

}