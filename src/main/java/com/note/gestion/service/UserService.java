package com.note.gestion.service;

import com.note.gestion.mapper.UserMapper;
import com.note.gestion.model.Grade;
import com.note.gestion.model.Group;
import com.note.gestion.model.UserHei;
import com.note.gestion.repository.GroupRepository;
import com.note.gestion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private GradeService gradeService;

    //GET mapping
        //1.get all users
    public List<UserHei> getAllUsers(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return userRepository.findAll(pageable).toList();
    }

        //2.get one user.role.STUDENT
    @Transactional
    public UserHei getUserByAttributes(String ref){
        UserHei THIS_USER = userRepository.findByRef(ref);
        Float STUDENT_AVERAGE = 0F;
        Integer totalCoef = 0;
        List<Grade> COURSE_GRADES = gradeService.getAllGradeOfOneStudent(THIS_USER.getIdUser());
       for(Grade grade : COURSE_GRADES){
           STUDENT_AVERAGE += grade.getAverage();
           totalCoef += grade.getEvaluation().getCourse().getCoef();
       }
       STUDENT_AVERAGE = STUDENT_AVERAGE / totalCoef;
        THIS_USER.setStudentAverage(STUDENT_AVERAGE);
        return THIS_USER;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //POST mapping
    public UserHei insertUser(UserMapper USER){
        UserHei NEW_USER =  new UserHei();
        Group GROUP = groupRepository.findById(USER.getIdGroupe()).orElseThrow(()->new NullPointerException("not found"));
        NEW_USER.setAddress(USER.getAddress());
        NEW_USER.setBirthDate(USER.getBirthDate());
        NEW_USER.setEmail(USER.getEmail());
        NEW_USER.setEntranceDatetime(USER.getEntranceDatetime());
        NEW_USER.setFirstName(USER.getFirstName());
        NEW_USER.setLastName(USER.getLastName());
        NEW_USER.setPhone(USER.getPhone());
        NEW_USER.setRef(USER.getRef());
        NEW_USER.setSex(USER.getSex());
        NEW_USER.setStatus(USER.getStatus());
        NEW_USER.setGroup(GROUP);
        userRepository.save(NEW_USER);
        return NEW_USER;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //PUT mapping
    public UserHei putModification(Long idUser, UserMapper USER_MODIFIED){
        UserHei USER = userRepository.findById(idUser).orElseThrow(()->new NullPointerException("not found"));
        Group GROUP = groupRepository.findById(USER_MODIFIED.getIdGroupe()).orElseThrow(()->new NullPointerException("not found"));

        if(USER.getAddress() != USER_MODIFIED.getAddress()){
            USER.setAddress(USER_MODIFIED.getAddress());
        }
        if(USER.getEmail() != USER_MODIFIED.getEmail()){
            USER.setEmail(USER_MODIFIED.getEmail());
        }
        if(USER.getBirthDate() != USER_MODIFIED.getBirthDate()){
            USER.setBirthDate(USER_MODIFIED.getBirthDate());
        }
        if(USER.getEntranceDatetime() != USER_MODIFIED.getEntranceDatetime()){
            USER.setEntranceDatetime(USER_MODIFIED.getEntranceDatetime());
        }
        if(USER.getFirstName() != USER_MODIFIED.getFirstName()){
            USER.setFirstName(USER_MODIFIED.getFirstName());
        }
        if(USER.getLastName() != USER_MODIFIED.getLastName()){
            USER.setLastName(USER_MODIFIED.getLastName());
        }
        if(USER.getRef() != USER_MODIFIED.getRef()){
            USER.setRef(USER_MODIFIED.getRef());
        }
        if(USER.getPhone() != USER_MODIFIED.getPhone()){
            USER.setPhone(USER_MODIFIED.getPhone());
        }
        userRepository.save(USER);
        return USER;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DELETE mapping
    public String deleteUser(Long idUser){
        userRepository.deleteById(idUser);
        return "user delete successfully";
    }
}
