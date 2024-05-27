package com.kodnest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodnest.entity.Playlist;

@Service
public interface PlaylistService {

	boolean playlistExists(Playlist playList);

	void savePlaylist(Playlist playList);

	List<Playlist> fetchAllPlaylists();


}