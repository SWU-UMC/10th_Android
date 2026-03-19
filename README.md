# 10th_Android

<h2>안드로이드 팀원 구성</h2>
<div style="overflow-x: auto;">

<table>
  <tr>
    <td align="center">도리/ 김도연(팀장)</td>
    <td align="center">제로/ 정규은(팀원)</td>
    <td align="center">레몬/ 김지아(팀원)</td>
    <td align="center">우가/ 강수아(팀원)</td>
    <td align="center">묵은지/ 임은지(팀원)</td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/bfa0f759-26cb-433e-b56f-96009de570a7" width="120"><br/>
      <a href="https://github.com/kimdoyeon1234">@kimdoyeon1234</a>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/a4ad7c51-c7df-42c0-a5b9-6a2258b50413" width="120"><br/>
      <a href="https://github.com/jeongkyueun">@jeongkyueun</a>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/336385ec-ebe4-4f55-b760-e84b94c8af41" width="120"><br/>
      <a href="https://github.com/Lemon0610">@Lemon0610</a>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/ef3ff44e-0cbe-4972-83de-33268d441fc4" width="120"><br/>
      <a href="https://github.com/mookeunji05">@mookeunji05</a>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/66418268-2913-4ba0-b966-77f2018b5c98" width="120"><br/>
    </td>
  </tr>
</table>

</div>

### Git 작업 순서

본인의 브랜치에서만 작업하고, `main` 브랜치는 직접 수정하지 않습니다.

#### 1. 현재 브랜치 확인
```sh
git branch
```

#### 2. 변경 사항 확인
```sh
git status
```

#### 3. 변경된 파일 추가
```sh
git add .
```

#### 4. 커밋 메시지 작성
```sh
git commit-m "Commit Message"
```

#### 5. 원격 저장소에 본인 브랜치로 푸시
```sh
git push origin <브랜치명>
```

### Commit Convention

커밋 메시지는 `타입: n주차 미션 설명`의 형식을 갖추어 작성합니다.

| 타입      | 설명                           |
|-----------|--------------------------------|
| feat      | 새로운 기능 추가               |
| fix       | 버그 수정                      |
| refactor  | 코드 리팩토링                  |
| docs      | 문서 수정 (README 등)          |
| style     | 코드 스타일 변경 (세미콜론 추가 등)|
| chore     | 빌드 및 패키지 설정 변경       |
| test      | 테스트 코드 추가               |

#### Commit Example
```sh
git commit -m "feat: 1주차 미션 화면 전환 기능"
git commit -m "fix: 5주차 미션 API 응답 오류 수정"
```

### PR Convention

- Pull Request(PR)은 미션 별로 생성합니다.
- PR 제목은 `n주차 미션` 형식으로 작성합니다.
- 파트장이 승인한 후, main 브랜치로 Merge 합니다.
