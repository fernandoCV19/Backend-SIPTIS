package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.service.records.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository usuarioCommonRepository;
    private final ActivityService activityService;


    @Override
    public ServiceAnswer findAll() {
        List<SiptisUser> userDBList = usuarioCommonRepository.findAll();
        List<UserGeneralInformationDTO> userList = new ArrayList<>();
        for (SiptisUser user: userDBList) {
            UserGeneralInformationDTO userDTO = convertToDTO(user);

            userList.add(userDTO);

        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(userList).build();
    }

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id) {
        return null;
    }

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id) {
        Optional<SiptisUser> usuarioOptional = usuarioCommonRepository.findById(id);
        if(usuarioOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }

        return null;
    }

    @Override
    public boolean existsByEmail(String email){
        return usuarioCommonRepository.existsByEmail(email);
    }

    @Override
    public SiptisUser findByEmail(String email){

        Optional<SiptisUser> user = usuarioCommonRepository.findByEmail(email);
        return user.get();
    }


    @Override
    public boolean existsTokenPassword(String tokenPassword){

        return usuarioCommonRepository.existsByTokenPassword(tokenPassword);
    }

    @Override
    public SiptisUser findByTokenPassword(String tokenPassword){

        Optional<SiptisUser> optional = usuarioCommonRepository.findByTokenPassword(tokenPassword);
        return optional.get();
    }


    @Override
    public ServiceAnswer getAllUsers() {
        List<SiptisUser> userList = usuarioCommonRepository.findAll();
        return userList.isEmpty() ?
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(userList).build() :
                ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(usuarioCommonRepository.findAll()).build();
    }



    @Override
    public SiptisUser save(SiptisUser siptisUser){
        return usuarioCommonRepository.save(siptisUser);
    }


    private UserGeneralInformationDTO convertToDTO(SiptisUser user){
            UserGeneralInformationDTO userDTO = new UserGeneralInformationDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            return userDTO;
        }
        @Override
        public ServiceAnswer getPersonalActivities(Long id){
            Optional<SiptisUser> userFound = usuarioCommonRepository.findById(id);
            if(userFound.isEmpty()){
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
            }
            ProjectStudent ps = userFound.get().getStudents().iterator().next();
            List<Activity> activities = ps.getProject().getActivities().stream().toList();
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(activities).build();
        }

    }

