package com.king.team.service;

/**
 * @Author Manix
 * @Description teamException异常类
 * @Param
 * @return
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public TeamException() {
    }

    public TeamException(String message) {
        super(message);
    }
}
