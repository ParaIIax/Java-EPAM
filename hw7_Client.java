package ua.univer.task7;

public class Client {
    private Deposit [] clientDeposits;
    //Private field deposits (client deposits) – objects array of type Deposit


    public Client() {
        this.clientDeposits = new Deposit[10];
    }
    //Constructor without parameters, which creates empty array deposits
    //consisting of 10 elements


    public boolean addDeposit(Deposit deposit) {
        for (int i = 0; i < clientDeposits.length; ++i) {
            if (clientDeposits[i] == null) {
                clientDeposits[i] = deposit;
                return true;
            }
        }
        return false;
    }
    //Method addDeposit with parameter deposit for adding regular, special or
    //long-term account into array on the first empty spot and returning true, or
    //returning false, if accounts number limit is depleted (no empty space in array).

    public double totalIncome() {
        double totalIncome = 0;
        for (Deposit deposit: clientDeposits) {
            if (deposit != null)
            totalIncome += deposit.income();
        }
        return totalIncome;
    }
    //Method totalIncome, returning total income amount based on all client’s
    //deposits upon deposits expiration.

    public double maxIncome() {
        Deposit maxDeposit = this.clientDeposits[0];
        for (int i = 1; i < clientDeposits.length; i++) {
            if (clientDeposits[i] != null) {
                if (clientDeposits[i].income() > maxDeposit.income()) {
                    maxDeposit = clientDeposits[i];
                }
            }
        }
        return maxDeposit.income();
    }
    //Method maxIncome, returning maximum deposit income of all client’s
    //deposits upon deposits expiration.

    public double getIncomeByNumber(int depositNumber) {
        --depositNumber;
        if (depositNumber > clientDeposits.length || depositNumber < 0)
            return 0;
        try{
            if(clientDeposits[depositNumber] == null){
                throw new Exception("This deposit number does not exist");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
        return clientDeposits[depositNumber].income();
    }
    //Method getIncomeByNumber with integer parameter number (deposit
    //number, which equals its index in array, increased by one), returning income
    //from deposit with such number. If deposit with such number does not exist,
    //method returns 0 value.
}
