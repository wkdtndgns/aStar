/**
 * test2
 *
 * @author shjang02 < shjang02@simplexi.com >
 * @since 2023. 05. 17.
 */
// A $( document ).ready() block.
$(document).ready(function () {
    $('#btnRandomWall').click(function () {
        // 장애물 생성
        // create emptiness
        for (var x = 0; x < worldWidth; x++) {
            world[x] = [];

            for (var y = 0; y < worldHeight; y++) {
                world[x][y] = 0;
            }
        }

        for (var x = 0; x < worldWidth; x++) {
            for (var y = 0; y < worldHeight; y++) {
                if (Math.random() > 0.75) world[x][y] = 1;
            }
        }
        redraw();
    });

    $('#btnResetWall').click(function () {
        // 장애물 생성
        // create emptiness
        for (var x = 0; x < worldWidth; x++) {
            world[x] = [];

            for (var y = 0; y < worldHeight; y++) {
                world[x][y] = 0;
            }
        }
        redraw();
    });
});
