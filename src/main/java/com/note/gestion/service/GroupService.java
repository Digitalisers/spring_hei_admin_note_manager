package com.note.gestion.service;

import com.note.gestion.model.GroupHei;
import com.note.gestion.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {
    private GroupRepository groupRepository;

    //GET mapping
    public List<GroupHei> getAllGroup(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return groupRepository.findAll(pageable).toList();
    }

    //POST mapping
    public GroupHei insertGroup(GroupHei newGroupHei){
        GroupHei groupHei = new GroupHei();
        groupHei.setName(newGroupHei.getName());
        groupHei.setCreationDatetime(newGroupHei.getCreationDatetime());
        groupHei.setRef(newGroupHei.getRef());
        groupRepository.save(groupHei);
        return groupHei;
    }

    //PUT mapping
    public GroupHei putModification(Long idGroup, GroupHei GROUP_Hei_MODIFIED){
        GroupHei GROUPHei = groupRepository.findById(idGroup).orElseThrow(()-> new NullPointerException("not found"));
        if(GROUP_Hei_MODIFIED.getName() != GROUPHei.getName()){
            GROUPHei.setName(GROUP_Hei_MODIFIED.getName());
        }
        if(GROUP_Hei_MODIFIED.getRef() != GROUPHei.getRef()){
            GROUPHei.setRef(GROUP_Hei_MODIFIED.getRef());
        }
        if(GROUP_Hei_MODIFIED.getCreationDatetime() != GROUPHei.getCreationDatetime()){
            GROUPHei.setCreationDatetime(GROUP_Hei_MODIFIED.getCreationDatetime());
        }
        groupRepository.save(GROUPHei);
        return GROUPHei;
    }

    //DELETE mapping
    public String deleteGroup(Long idGroup){
        groupRepository.deleteById(idGroup);
        return "groupHei deleted successfully";
    }
}
