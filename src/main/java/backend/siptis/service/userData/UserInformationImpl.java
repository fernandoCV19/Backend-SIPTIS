package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.PersonalInformationDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.AdminListItemDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.UserPersonalInformationDTO;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserInformationImpl implements UserInformationService{

    private final UserInformationRepository userInformationRepository;


    private boolean existUserByCodSIS(String codSIS) {
        return userInformationRepository.existsByCodSIS(codSIS);
    }

    private boolean existUserByCi(String ci) {
        return userInformationRepository.existsByCi(ci);
    }

    private boolean existsUserById(Long id) {
        return userInformationRepository.existsById(id.intValue());
    }

    private UserInformation findUserInformationById(Long id){
        return userInformationRepository.findById(id).get();
    }

    @Override
    public ServiceAnswer existByCodSIS(String codSIS) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(existUserByCodSIS(codSIS)).build();
    }

    @Override
    public ServiceAnswer existByCi(String ci) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(existUserByCi(ci)).build();
    }

    @Override
    public ServiceAnswer existsById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(existsUserById(id)).build();
    }

    @Override
    public ServiceAnswer findById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(findUserInformationById(id)).build();
    }

    @Override
    public ServiceAnswer getAllAdmin() {
        List<AdminListItemDTO> userList =
                userInformationRepository.getAllAdmins();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer userEditLimitedInformation(UserEditPersonalInformationDTO dto) {
        //validaciones

        return null;
    }

    @Override
    public ServiceAnswer adminEditUserFullInformation(AdminEditUserPersonalInformationDTO dto) {
        return null;
    }


    @Override
    public ServiceAnswer getAllStudents() {
        List<UserListItemDTO> userList =
                userInformationRepository.getAllUsersByRole(1L);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer getAllTeachers() {
        List<UserListItemDTO> userList =
                userInformationRepository.getAllUsersByRole(3L);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer searchUserByNameAndRole(String name, Long role_id) {
        List<UserListItemDTO> users =
                userInformationRepository.searchUserByNameAndRole(name, role_id);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(users).build();

    }

    @Override
    public ServiceAnswer getUserPersonalInformation(SiptisUser user) {
        PersonalInformationDTO personalInfo = new PersonalInformationDTO();
        if(user != null){

            personalInfo.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();;
            if(information != null){
                personalInfo.setNames(information.getNames());
                personalInfo.setLastnames(information.getLastnames());
                personalInfo.setCelNumber(information.getCelNumber());
                personalInfo.setCi(information.getCi());
                personalInfo.setBirthDate(information.getBirthDate());
                personalInfo.setCodSIS(information.getCodSIS());
            }
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK).data(personalInfo).build();

    }


    @Override
    public ServiceAnswer getTeacherNotSelectedAreasById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userInformationRepository.getNotSelectedAreas(id)).build();

    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterUserDTO dto) {

        ServiceAnswer answer = validateNames(dto.getNames());
        if(answer != null){
            return answer;
        }
        answer = validateLastNames(dto.getLastnames());
        if(answer != null){
            return answer;
        }
        answer = validateCi(dto.getCi());
        if(answer != null){
            return answer;
        }
        answer = validateCelNumber(dto.getCelNumber());
        if(answer != null){
            return answer;
        }
        answer = validateCodSis(dto.getCodSIS());
        if(answer != null){
            return answer;
        }

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames());
        userInformation.setLastnames(dto.getLastnames());
        userInformation.setCi(dto.getCi());
        userInformation.setBirthDate(dto.getBirthDate());
        userInformation.setCodSIS(dto.getCodSIS());

        return createAnswer(ServiceMessage.OK, userInformation);
    }


    @Override
    public ServiceAnswer registerStudentInformation(StudentRegisterDTO dto) {
        return null;
    }

    @Override
    public ServiceAnswer registerTeacherInformation(TeacherRegisterDTO dto) {
        return null;
    }


    private ServiceAnswer validateNames(String names){
        String errorMessage = "";
        if(names == null || names == ""){
            errorMessage = "Debe ingresar algun nombre.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(names.length() < 3){
            errorMessage = "El nombre es demasiado corto.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(names.length() > 25){
            errorMessage = "El nombre es demasiado largo.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        return null;
    }

    private ServiceAnswer validateLastNames(String lastnames){
        String errorMessage = "";
        if(lastnames == null || lastnames == ""){
            errorMessage = "Debe ingresar algun apellido.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(lastnames.length() < 3){
            errorMessage = "El apellido es demasiado corto.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(lastnames.length() > 30){
            errorMessage = "El apellido es demasiado largo.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        return null;
    }

    private ServiceAnswer validateCelNumber(String celNumber){
        String errorMessage = "";
        if(celNumber == null || celNumber == ""){
            errorMessage = "Debe ingresar algun numero personal.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(celNumber.length() < 6){
            errorMessage = "El numero es demasiado corto.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(celNumber.length() > 11){
            errorMessage = "El numero es demasiado largo.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        return null;
    }

    private ServiceAnswer validateCi(String ci){
        String errorMessage = "";
        if(ci == null || ci == ""){
            errorMessage = "Debe ingresar algun documento de identidad.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(ci.length() < 5){
            errorMessage = "El documento de identidad es demasiado corto.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(ci.length() > 15){
            errorMessage = "El documento de identidad es demasiado largo.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }

        if(existUserByCi(ci)){
            errorMessage = "El ci ya se encuentra registrado en el sistema";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        return null;
    }

    private ServiceAnswer validateCodSis(String codSis){
        String errorMessage = "";
        if(codSis == null || codSis == ""){
            errorMessage = "Debe ingresar algun codigo Sis.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(codSis.length() < 6){
            errorMessage = "El codigo Sis es demasiado corto.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(codSis.length() > 15){
            errorMessage = "El codigo Sis es demasiado largo.";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }

        if(existUserByCodSIS(codSis)){
            errorMessage = "El codigo Sis ya se encuentra registrado en el sistema";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        return null;
    }

    private ServiceAnswer createAnswer(ServiceMessage message, Object data){
        return ServiceAnswer.builder().serviceMessage(message)
                .data(data).build();

    }
}
