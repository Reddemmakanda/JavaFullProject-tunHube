package com.kodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.entity.Playlist;
import com.kodnest.entity.Song;
import com.kodnest.service.PlaylistService;
import com.kodnest.service.SongService;

@Controller
public class PlaylistController {
	@Autowired
	PlaylistService playlistService;
	
	
	
	@Autowired
	SongService songService;
	
	@GetMapping("/createplaylists")
	public String createPlaylist(Model model) {
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createplaylist";
	}

	
	
	
	@PostMapping("/addplaylist")

	public String addPlaylist( @ModelAttribute Playlist playList) {

		//			Checking Song is present or not
		boolean playlistExists = playlistService.playlistExists(playList);

		if(playlistExists==false) {
			playlistService.savePlaylist(playList);

			System.out.println("Playlist added successfully");
		}

		else {

			System.out.println("Duplicate Playlist");

		}
		return "adminhome";

	}

	
	@GetMapping("/viewplaylists")
	public String viewPlaylists(Model model) {
	    List<Playlist> playlists = playlistService.fetchAllPlaylists(); // Update method name
	    model.addAttribute("playlists", playlists);
	    return "viewplaylists";
	}

	
	





    
}