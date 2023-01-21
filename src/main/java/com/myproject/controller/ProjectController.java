package com.myproject.controller;

import com.myproject.dto.ProjectDTO;
import com.myproject.entity.ResponseWrapper;
import com.myproject.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @RolesAllowed({"Manager","Admin"})
    @GetMapping
    public ResponseEntity<ResponseWrapper> getProjects() {
        List<ProjectDTO> projectDTOList = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOList, HttpStatus.OK));
    }

    //getProjectByCode
    @RolesAllowed({"Manager"})
    @GetMapping("/{projectCode}")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("projectCode") String code){
        ProjectDTO projectDTO = projectService.getByProjectCode(code);
        return ResponseEntity.ok(new ResponseWrapper("Project successfully retrieved",projectDTO,HttpStatus.OK));
    }

    //createProject
    @RolesAllowed({"Manager"})
    @PostMapping
    protected ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.save(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Projects are successfully created",projectDTO,HttpStatus.CREATED));
    }
    //updateProject
    @RolesAllowed({"Manager"})
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project) {
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("Project updated successfully", project, HttpStatus.OK));
    }
//    deleteProject
    @RolesAllowed({"Manager"})
    @DeleteMapping("/{projectId}")
    ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectId") String project) {
        projectService.delete(project);
        return ResponseEntity.ok(new ResponseWrapper("Project successfully delted", project , HttpStatus.OK));
    }
    //getProjectByManager
    @GetMapping("/manager/project-status")
    @RolesAllowed({"Manager"})
    ResponseEntity<ResponseWrapper> getProjectByManager() {
        List<ProjectDTO> projects = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved",projects,HttpStatus.OK));
    }
    //managerCompleteProject
    @RolesAllowed({"Manager"})
    @PutMapping("/manager/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String code){
        projectService.complete(code);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed",HttpStatus.OK));

    }


}
