pragma solidity >=0.4.24 < 0.6.11;

contract Account {
    address _owner;
    
    mapping(address => uint256) balance;
    
    event Transfer(address _from, address _to, uint256 amount);
    event Deposit(address account, uint256 amount);
    event WithDrawal(address account, uint256 amount);
    
    function getBalance(address account) public constant returns (uint256)
    {
        if (tx.origin == _owner) {
            return balance[account];    
        }
    }
    
    function getBalance() public constant returns (uint256)
    {
        return balance[tx.origin];    
    }


    function deposit(address account, uint256 amount) public returns (bool)
    {
        bool _result = false;
        if (tx.origin == _owner) {
            uint256 _rest = balance[account];
            balance[account] = _rest + amount;
            _result = true;
            emit Deposit(account, amount);
        }
        return _result;
    }
    
    function withdrawal(address account, uint256 amount) public returns (bool)
    {
        bool _result = false;
        if (tx.origin == _owner) {
            uint256 _rest = balance[account];
            if (_rest <= amount) {
                balance[account] = _rest - amount;   
                _result = true;
                emit WithDrawal(account, amount);
            }
        }
        return _result;
    }


    function transfer(address _to, uint256 amount) public returns (bool)
    {
        bool _result = false;
        uint256 _rest = balance[tx.origin];
        if (amount <= _rest) {
            balance[tx.origin] = _rest - amount; 
            balance[_to] += amount;
            _result = true;
            emit Transfer(tx.origin, _to, amount);
        }
        return _result;
    }
    
    constructor() public {
        _owner = tx.origin;
    }
}