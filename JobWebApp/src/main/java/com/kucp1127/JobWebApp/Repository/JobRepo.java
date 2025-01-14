package com.kucp1127.JobWebApp.Repository;

import com.kucp1127.JobWebApp.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class JobRepo {
    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<JobPost> returnAllJobPosts() {
        String sql = "select * from jobPost";
        return jdbc.query(sql ,(rs , rowNum)->{
            JobPost job = new JobPost();
            job.setPostId(rs.getInt("postId"));
            job.setPostProfile(rs.getString("postProfile"));
            job.setPostDesc(rs.getString("postDesc"));
            job.setReqExperience(rs.getInt("reqExperience"));
            Array sqlArray = rs.getArray("postTechStack");
            String[] techStackArray = (String[]) sqlArray.getArray();
            job.setPostTechStack(Arrays.asList(techStackArray));
            return job;
        });
    }

    public void addJobPost(JobPost job) {
        String sql = "insert into jobPost (postId, postProfile, postDesc, reqExperience, postTechStack) values (?,?,?,?,?)";
        try {
            Array techStackArray = jdbc.getDataSource()
                    .getConnection()
                    .createArrayOf("text", job.getPostTechStack().toArray());

            jdbc.update(sql,
                    job.getPostId(),
                    job.getPostProfile(),
                    job.getPostDesc(),
                    job.getReqExperience(),
                    techStackArray);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while converting List<String> to SQL Array", e);
        }
    }

}
