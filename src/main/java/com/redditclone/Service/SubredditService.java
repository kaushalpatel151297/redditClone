package com.redditclone.Service;

import com.redditclone.DTO.SubredditDTO;
import com.redditclone.Exception.SpringBootApplicationException;
import com.redditclone.Model.Subredit;
import com.redditclone.Reposiotry.SubredditReposiotry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubredditService {

    @Autowired
    private SubredditReposiotry subredditReposiotry;

    @Autowired
    private ModelMapper modelmapper;

    public SubredditDTO createSubreddit(SubredditDTO subredditDTO)
    {
        Subredit map = this.modelmapper.map(subredditDTO, Subredit.class);
        Subredit save = this.subredditReposiotry.save(map);
        return this.modelmapper.map(save, SubredditDTO.class);
    }
    public List<SubredditDTO> getAllSubreddit()
    {
        List<Subredit> all = this.subredditReposiotry.findAll();
        List<SubredditDTO> collect = all.stream().map(sub -> modelmapper.map(sub, SubredditDTO.class)).collect(Collectors.toList());
        return collect;
    }
    public SubredditDTO getSubrediById(Long subId)
    {
        Subredit byId;
        byId = this.subredditReposiotry.findById(subId).get();
        return  this.modelmapper.map(byId, SubredditDTO.class);
    }
}
