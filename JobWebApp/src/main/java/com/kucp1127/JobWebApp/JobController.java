package com.kucp1127.JobWebApp;

import com.kucp1127.JobWebApp.model.JobPost;
import com.kucp1127.JobWebApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class JobController {

    @Autowired
    public JobService jobService;

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping({"/","home"})
    public String home(){
        System.out.println("adarsh");
        return "home";
    }
    @RequestMapping("addjob")
    public String addJob(){
        return "addJob";
    }


    @PostMapping("/handleForm")
    public String handleAddJobForm(JobPost jobPost) {
        jobService.addJob(jobPost);
        return "success";
    }

    @RequestMapping("viewalljobs")
    public String viewAllJobs(Model mv){
        List<JobPost> jobs = jobService.getAllJob();
        mv.addAttribute("jobPosts" , jobs);
        return "viewAllJobs";
    }
}
