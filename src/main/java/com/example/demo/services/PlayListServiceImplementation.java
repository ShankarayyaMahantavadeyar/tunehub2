package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlayListRepository;

@Service
public class PlayListServiceImplementation implements PlayListService
{

	@Autowired
	PlayListRepository repo;
	
	@Override
	public void addPlaylist(Playlist playlist) 
	{
		repo.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchAllPlaylists() 
	{
		return repo.findAll();
	}

}