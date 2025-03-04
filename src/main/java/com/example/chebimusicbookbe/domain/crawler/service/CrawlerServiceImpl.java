package com.example.chebimusicbookbe.domain.crawler.service;


import com.example.chebimusicbookbe.domain.crawler.exception.AlbumArtDataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class CrawlerServiceImpl implements CrawlerService {
    private static final Logger log = LoggerFactory.getLogger(CrawlerServiceImpl.class);

    @Override
    public List<String> albumArtCrawl(String keyword) {
        String formattedKeyword = keyword.replaceAll(" ", "%20");
        String url = String.format("https://music.bugs.co.kr/search/track?q=%s&target=ARTIST_TRACK_ALBUM&flac_only=false&sort=A", formattedKeyword);

        List<String> imgList = new ArrayList<>();

        try {
            // Jsoup을 이용하여 HTML 문서를 가져옴
            Document doc = Jsoup.connect(url).get();
            // 검색된 결과의 개수를 가져옴
            Elements numElements = doc.select("#container > section > div > fieldset > div > table > tbody > tr > td:nth-child(2) > a > span");
            // 괄호와 쉼표를 제거하고 Elements 타입을 int로 변환
            int num = Integer.parseInt(numElements.text().replace("(", "").replace(")", "").replace(",", ""));
            // 검색된 데이터가 없을 경우 에외 처리
            if (numElements.isEmpty()) {
                throw new AlbumArtDataNotFoundException("[ERROR] Album Art data not found]");
            } else {
                System.out.println(num + "개 검색됨");
            }

            // 앨범 아트 이미지 URL 가져옴
            Elements elements = doc.select("#DEFAULT0 > table > tbody > tr td img");
            Set<String> uniqueImgSet = new HashSet<>();
            for (var element : elements) {
                String imgUrl = element.attr("src").replace("images/50", "images/500");
                uniqueImgSet.add(imgUrl);
            }
            imgList.addAll(uniqueImgSet);
        } catch (IOException e) {
            // 예외 발생시 스택 트레이스 출력
            e.printStackTrace();
        }
        return imgList;
    }
}
