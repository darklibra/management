package org.spring.management.member.model;

public class UserPaging {
    private int currentPage = 1;
    private int countPerPage = 10;
    private int maxPage = 1;
    
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCountPerPage() {
        return countPerPage;
    }
    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }
    public int getMaxPage() {
        return maxPage;
    }
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    
    
}
