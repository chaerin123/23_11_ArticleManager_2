package com.KoreaIT.java.AM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public void main(String[] args) {
    System.out.println("==프로그램 시작==");

    Scanner sc = new Scanner(System.in);
    int lastArticleId=0;
    List<Article> articles = new ArrayList<>();

    while (true){
      System.out.print("명령어 ) ");
      String cmd = sc.nextLine().trim();
      //trim()메소드: string의 시작부분과 끝부분의 공백을 지워줌
      //사용자가 입력한 문자열의 앞뒤의 공백이 제거되어 깔끔한 문자열을 얻음

      if(cmd.length() == 0){ //명령어를 입력하지 않은 경우
        continue;
      } //else{System.out.println("게시글 존재");}

      if(cmd.equals("system exit")){
        break;
      }

      //글 입력
      if(cmd.equals("article write")){
        int id = lastArticleId + 1; //여기서 lastArticleId는 값
        lastArticleId = id;//여기에서 lastArticleId 변수

        String regDate = Util.getNowDateStr();
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String body = sc.nextLine();

        Article article = new Article(id, title, body);
        articles.add(article);
        System.out.printf("%d번 글이 생성되었습니다.\n", id);

      } else if(cmd.equals("article list")) { //글 목록보기
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다.");
        } else {
          System.out.println("번호   |   제목");
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%d   |    %s\n", article.id, article.title);
          }
        }
      } else if (cmd.startsWith("article delete")) { //글 지우기
        String[] cmdBits = cmd.split(" ");//cmdBits는 여기에서 String타입이라고 선언, 공백자를 기준으로 쪼개기
        int id = Integer.parseInt(cmdBits[2]); //cmdBits는 현재 문자타입 "2"-->를 정수타입 2로 변경하는 부분

        int foundIdx = -1; //인덱스는 0부터 시작, :인덱스를 못찾았다는 의미
        for(int i = 0; i < articles.size(); i++){ //찾고자 하는 게시물의 번호를 하나씩 대조해서 순회
          Article article = articles.get(i);
          if(article.id == id){
            foundIdx = i;
            break;
          }
        }

        if(foundIdx == -1){
          System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
          continue;
        }
        articles.remove(foundIdx);
        System.out.printf("%d번 게시물이 삭제되었습니다\n", id);
      }

      else if (cmd.startsWith("article detail")) { //startsWith: boolean타입/ 뒤에 "" 안에 쓴 접두사로 시작하는지
        String[] cmdBits=cmd.split(""); //cmdBits는 여기에서 String타입이라고 선언, 공백자를 기준으로 쪼개기
        int id = Integer.parseInt(cmdBits[2]); //cmdBits는 현재 문자타입 "2"-->를 정수타입 2로 변경하는 부분
        //래퍼클래스

        Article foundArticle = null;

        for(int i =0; i < articles.size(); i++){ //찾고자 하는 게시물의 번호를 하나씩 대조해서 순회
          Article article = articles.get(i);
          if(article.id == id){
            foundArticle = article;
            break;
          }
        }

        if(foundArticle == null){
          System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
          continue;
        }

        System.out.printf("번호 : %d\n", foundArticle.id);
        System.out.printf("날짜 : %s\n", foundArticle.regDate);
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.body);


      } else if(cmd.startsWith("article modify")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = null;
        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }

        if (foundArticle == null) {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
          continue;
        }

        System.out.print("제목:  ");
        String title = sc.nextLine();
        System.out.print("내용:  ");
        String body = sc.nextLine();

        foundArticle.title = title;
        foundArticle.body = body;
        System.out.printf("%d번 게시물이 수정 되었습니다. \n", id);
      } else {
        System.out.println("존재하지 않는 명령어입니다.\n");
      }
    }

    System.out.println("==프로그램 종료==");
    sc.close();
  }
}

class Article{
  int id;
  String regDate;
  String title;
  String body;

  public Article(int id, String regDate, String title){
    this.id = id;
    this.regDate = regDate;
    this.title = title;
    this.body = body;
  }
}
