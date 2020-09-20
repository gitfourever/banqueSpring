package ang.neggaw.metier;

import ang.neggaw.entities.UserBank;

public interface IUserBankMetier {

    public String addUserBank(UserBank userBank);
    public RuntimeException modifyPasswordUserBank(String email, String oldPass, String newPass, String newRePass);
}
