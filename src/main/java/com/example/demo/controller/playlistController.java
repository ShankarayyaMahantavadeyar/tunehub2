package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class playlistController 
{
	@Autowired
	SongService songService;
	
	@Autowired
	PlayListService playlistService;
	
	@GetMapping("/createplaylist")
	public String createplaylist(Model model)
	{
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList); 
		return "createplaylist";	
	}
	
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist)
	{
		//updating playlist table
		playlistService.addPlaylist(playlist);
		//updating song table
		List<Song> songList=playlist.getSongs();
		for(Song s:songList)
		{
			
			s.getPlaylists().add(playlist);
			songService.updateSong(s);
			
		}
		return "adminhome";	
	}
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) 
	{
		List<Playlist> allPlaylists=playlistService.fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists); 
		return "displayPlaylists";
	}
	

}