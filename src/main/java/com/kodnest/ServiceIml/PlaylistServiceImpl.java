package com.kodnest.ServiceIml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.Repository.PlaylistRepository;
import com.kodnest.entity.Playlist;
import com.kodnest.service.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	
	@Autowired
	PlaylistRepository playlistRepository;

	@Override
	public boolean playlistExists(Playlist playList) {
		Playlist existingplaylist =playlistRepository.findFirstByName(playList.getName());
		if(existingplaylist!=null) {
			System.out.println("Playlist Present");
			return true;
		}
		else {
			System.out.println("Playlist Absent");
			return false;
			
		}
	}

	@Override
	public void savePlaylist(Playlist playList) {
		playlistRepository.save(playList);		
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		List<Playlist> songs = playlistRepository.findAll();

		return songs;
	}

}