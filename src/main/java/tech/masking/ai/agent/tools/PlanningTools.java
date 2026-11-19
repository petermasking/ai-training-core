
package tech.masking.ai.agent.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import tech.masking.ai.rag.IngestionService;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

@Component
public class PlanningTools
{
    private static final Logger log =
        LoggerFactory.getLogger(IngestionService.class);

    @Tool(
        name = "calculateTrip",
        description = """
        Calculates business trip costs (travel and accommodation).
        Use this tool for calculating the cost of a business trip.
        """)
    public double calculateTrip(
        @ToolParam(description = "Departure date", required = true)
        String departureDate,

        @ToolParam(description = "Return date", required = true)
        String returnDate,

        @ToolParam(description = "Departure city name", required = true)
        String departureLocation,

        @ToolParam(description = "Destination city name", required = true)
        String destinationLocation
    ) {
        log.info("Calculated trip from {} to {} ({} - {})", departureLocation, destinationLocation, departureDate, returnDate);

        return 250.0;
    }

    @Tool(
        name = "bookTrip",
        description = """
        Books a business trip (travel and accommodation).
        Use this tool for booking a business trip.
        """)
    public String bookTrip(
        @ToolParam(description = "Departure date", required = true)
        String departureDate,

        @ToolParam(description = "Return date", required = true)
        String returnDate,

        @ToolParam(description = "Departure city name", required = true)
        String departureLocation,

        @ToolParam(description = "Destination city name", required = true)
        String destinationLocation
    ) {
        log.info("Booked trip from {} to {} ({} - {})", departureLocation, destinationLocation, departureDate, returnDate);

        return "Booking successful. You will travel by plane and stay at Hotel California.";
    }
}
