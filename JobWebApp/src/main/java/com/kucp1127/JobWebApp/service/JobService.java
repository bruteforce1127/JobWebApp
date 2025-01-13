package com.kucp1127.JobWebApp.service;

import com.kucp1127.JobWebApp.Repository.JobRepo;
import com.kucp1127.JobWebApp.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepo jobRepository;

    public JobRepo getJobRepository() {
        return jobRepository;
    }

    public void setJobRepository(JobRepo jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void addJob(JobPost job){
        jobRepository.addJobPost(job);
    }
    public List<JobPost> getAllJob(){
        return jobRepository.returnAllJobPosts();
    }
}
