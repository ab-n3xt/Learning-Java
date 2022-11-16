package cinema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


class Seat {
    private int row;
    @JsonProperty("column")
    private int col;

    public Seat() {
    }

    public Seat(int rowNumber, int colNumber) {
        this.row = rowNumber;
        this.col = colNumber;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "{\n" + "\trow:" + this.row + ",\n" + "\tcolumn:" + this.col+ "\n}";
    }
}

@Component
class SeatMap {
    @JsonProperty("total_rows")
    private final int totalRows = 9;

    @JsonProperty("total_columns")
    private final int totalCols = 9;

    @JsonProperty("available_seats")
    List<Seat> seatList = new ArrayList<>();

    public int getTotalRows() {
        return this.totalRows;
    }

    public int getTotalCols() {
        return this.totalCols;
    }

    public List<Seat> getSeatList() {
        return this.seatList;
    }

    public void setSeatList(List<Seat> l) {
        this.seatList = l;
    }
}

@RestController
class SeatController {

    public List<Seat> initializeList(SeatMap seatMap) {
        int totalRows = seatMap.getTotalRows();
        int totalCols = seatMap.getTotalCols();

        List<Seat> seatList = seatMap.getSeatList();

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalCols; j++) {
                seatList.add(new Seat(i, j));
            }
        }

        return seatList;
    }


    @GetMapping("/seats")
    @Autowired
    public SeatMap getSeatMap(SeatMap seatMap) {
        seatMap.setSeatList(initializeList(seatMap));

        return seatMap;
    }
}

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
