package mylab.user.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
    private UserRepository userRepository;
        
    private SecurityService securityService;
        
    public UserRepository getUserRepository() { return userRepository; }
    public SecurityService getSecurityService() { return securityService; }
    
    public boolean registerUser(String userId, String name, String password) {
        if (securityService.authenticate(userId, password)) {
            return userRepository.saveUser(userId, name);
        }
        return false;
    }
}

public class SecurityService {
    
    public boolean authenticate(String userId, String password) {
        System.out.println("����: " + userId);
        return password != null && !password.isEmpty();
    }
    
    public boolean authorize(String userId, String resource) {
        System.out.println("���� �ο�: " + userId + " for " + resource);
        return true;
    }
}

public class UserRepository {
   private String dbType;
    
    public UserRepository() {}
    
    public String getDbType() { return dbType; }
    public void setDbType(String dbType) { this.dbType = dbType; }
    
    public boolean saveUser(String userId, String name) {
        System.out.println("����� ����: " + userId + ", " + name + " (DB: " + dbType + ")");
        return true;
    }
    
    @Override
    public String toString() {
        return "UserRepository [dbType=" + dbType + "]";
    }
}